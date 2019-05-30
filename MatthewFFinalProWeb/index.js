const express = require('express');
const app = express();
const session = require('express-session')
const PORT = process.env.PORT || 3000;

app.set('view engine', 'ejs')
app.set('views', './ejsviews')
app.use('/public', express.static(__dirname +'/public'));
app.use(express.urlencoded({extended: false}));
app.use(session({
    secret: 'mysupersecretcode !!',
    saveUninitialized: false,
    resave: false,
    cookie: {maxAge: 1000*60*60*24}
}));

const database = require('./database.js');
database.startDBandApp(app, PORT);

const flash = require('connect-flash')
app.use(flash())

const passConfig = require('./passConfig.js')
passConfig.config(app)

const utils = require('./utils.js');
const ShoppingCart = require('./model/ShoppingCart.js');

app.get('/',(req, res) =>{
    res.render('login',{flash_message: req.flash('flash_message')})
})

app.post('/login', passConfig.passport.authenticate(
    'localLogin',
    {successRedirect: '/storefront',failureRedirect: '/',failureFlash:true}
));

app.get('/signup',(req,res) =>{
    res.render('signup', {flash_message: req.flash('flash_message')});
})

app.post('/signup', passConfig.passport.authenticate(
    'signupStrategy',
    {successRedirect: '/',failureRedirect: '/signup',failureFlash:true}
));


app.get('/storefront', (req,res)=>{

    app.locals.bookCollection.find({}).toArray()
        .then(books =>{
            //res.send(books);
            res.render('storefront',{books, utils, user: req.user})
        })
        .catch(error =>{
            res.render('errorpage', {source: '/', error})
        })

})

app.get('/logout',(req,res)=>{
    req.logout();
    res.redirect('/');
})

app.post('/add2cart', (req,res) =>{
    let sc;
    if(!req.session.sc){
        sc = new ShoppingCart();
    }else {
        sc = ShoppingCart.deserialize(req.session.sc);
    }

    const _id = req.body._id;
    const title = req.body.title;
    const price = req.body.price;
    sc.add({_id, title, price});

    req.session.sc = sc.serialize();

    res.redirect('/showcart');
})

app.get('/showcart',auth, (req, res) =>{
    let sc;
    if(!req.session.sc){
        sc = new ShoppingCart();
    }else {
        sc = ShoppingCart.deserialize(req.session.sc);
    }
    res.render('showcart', {sc,utils})
})

app.get('/admin/home', authAsAdmin, (req, res) =>{
    app.locals.bookCollection.find({}).toArray()
        .then(books =>{
            res.render('admin/home',{books,utils})
        })
        .catch(error => {
            res.render('errorPage',{source: '/admin/home',error})
        })
})

app.get('/admin/insert',(req,res) =>{
    res.render('admin/insert')
})

app.post('/admin/insert',(req,res)=>{
    const image = req.body.image
    const title = req.body.title
    const price = req.body.price
    const description = req.body.description
    const book = {image,title,price,description}
    app.locals.bookCollection.insertOne(book)
    .then(result =>{
        res.redirect('home')
    })
    .catch(error=>{
        res.render('errorPage',{source:'/admin/insert',error})
    })
})

app.get('/admin/update',(req,res)=>{
    const _id = req.query._id
    app.locals.bookCollection.find({_id: database.ObjectID(_id)}).toArray()
    .then(books =>{
        console.log(books[0])
        if(books.length != 1){
            throw `found ${books.length} books for updating`
        }
        res.render('admin/update',{book:books[0]})
    })
    .catch(error =>{
        res.render('errorPage',{source: '/admin/update',error})
    })
})

app.post('/admin/update',(req,res)=>{
    const _id = req.body._id
    const image = req.body.image
    const title = req.body.title
    const price = req.body.price
    const description = req.body.description
    const query = {_id:database.ObjectID(_id)}
    const newValue = {$set:{image,title,price,description}}
    app.locals.bookCollection.updateOne(query, newValue)
    .then(result =>{
        res.redirect('/admin/home')
    })
    .catch(error =>{
        res.render('errorPage',{source:'/admin/update{POST}',error})
    })
})

app.post('/admin/delete',(req,res) =>{
    const _id = req.body._id
    const bookToDelete = {_id:database.ObjectID(_id)}
    app.locals.bookCollection.deleteOne(bookToDelete)
    .then(result =>{
        res.redirect('/admin/home')
    })
    .catch(error =>{
        res.render('errorPage',{source:'/admin/update{POST}',error})
    })
})

app.get('/admin/imageUpload',authAsAdmin,(req,res)=>{
    res.render('admin/imageUpload');
})


const multer = require('multer');
const path = require('path')

const MAX_FILESIZE = 1020 * 1024 * 1
const fileTypes = /jpeg|jpg|png|gif/;

const storageOptions = multer.diskStorage({
    destination: (req,file,callback)=>{
        callback(null,'./public/images');
    },
    filename: (req,file,callback)=>{
        callback(null,'image'+Date.now()+path.extname(file.originalname));
    }
});

const imageUpload = multer({
    storage: storageOptions,
    limits: {fileSize: MAX_FILESIZE},
    fileFilters: (req, file, callback) =>{
        const ext = fileTypes.test(path.extname(file.originalname).toLowerCase());
        const mimetype = fileTypes.test(file.mimetype);
        if(ext && mimetype){
            return callback(null,true)
        }else{
            return callback('Error: Images(jpeg, jpg, png, gif only');
        }
    }
}).single('imageButton');


app.post('/admin/imageUpload',authAsAdmin,(req,res)=>{
    imageUpload(req, res, error =>{
        if(error){
            return res.render('errorpage')
        }else if(!req.file){
            return res.render('errorpage')
        }

        const image = {filename: req.file.filename,size: req.file.size}
        app.locals.imageCollection.insertOne(image)
            .then(result =>{
                res.redirect('/admin/movies');
            })
            .catch(error =>{
                res.render('errorPage',{message: 'Image upload database error'})
            })
        //console.log('file Uploaded: ', req.file.filename);
        //res.send('Uploaded');
    })
})

app.get('/admin/movies',authAsAdmin,(req,res)=>{
    app.locals.imageCollection.find({}).toArray()
    .then(images =>{
        res.render('admin/movies',{images})
    })
    .catch(error => {
        res.render('errorPage',{source: '/admin/home',error})
    })
})

const fs = require('fs')

app.post('/admin/deleteImage', authAsAdmin, (req,res)=>{
    app.locals.imageCollection.deleteOne({_id: app.locals.ObjectID(req.body._id)})
    .then(result =>{
        const filename = req.body.filename;
        fs.unlink('./public/images/'+filename,(error)=>{
            if(error){
                res.render('errorPage', {message: 'fs.unlink error to delete file'})
            }else{
                res.redirect('/admin/movies')
            }
        })
    })
    .catch(error =>{
        res.render('errorPage', {message: 'image DB delete error'})
    })
})


function authAsAdmin(req,res,next){
    const user = req.user;
    if(!user || !user.admin){
        req.flash('flash_message', 'You are not an Admin')
        res.render('/')
    }else{
        next();
    }
}
function auth(req,res,next){
    const user = req.user;
    if(!user){
        req.flash('flash_message', 'You must sign in to view this page')
        res.redirect('/')
    }
    else{
        next();
    }
}
const crypto = require('crypto')

function hashPassword(tpassword){
    const len = 16;
    const salt = crypto.randomBytes(len/2).toString('hex');

    const hash = crypto.createHmac('sha512', salt);
    hash.update(tpassword);
    const hashed = hash.digest('hex')

    return {salt, hashed}
}

function verifyPassword(tpassword, user){
    const hash = crypto.createHmac('sha512', user.password.salt)
    hash.update(tpassword);
    const tpasswordHashed = hash.digest('hex')

    return tpasswordHashed == user.password.hashed;
}

module.exports = {hashPassword, verifyPassword}

//const p1 = 'password';
//const hp1 = hashPassword(p1);
//console.log(hp1);

//const salt = '30333daaea687efd'
//const hashed = '501175b4d1bf21958d62a8fdafabb3c93855ec87ad1cee09258d59153a559c439527e705aecf792924fa079eda791e748f9a4cdf3fb23aec14407ed7a46981ab'
//const user = {password:{salt,hashed}}
//console.log('verify', verifyPassword('password',user))
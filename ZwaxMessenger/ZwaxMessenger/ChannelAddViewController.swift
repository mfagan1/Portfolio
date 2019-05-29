//
//  ChannelAddViewController.swift
//  ZwaxMessenger


import UIKit
import Firebase
import FirebaseFirestore

class ChannelAddViewController: UIViewController {
    
    @IBOutlet var channelNameBox: UITextField!
    @IBOutlet var channelDescriptionBox: UITextView!
    @IBOutlet var userNameBox: UITextField!
    
    @IBAction func createChannel(_ sender: Any) {
        let collection = Firestore.firestore().collection("channels")
        
        guard let newChannel = Channel(channelName: channelNameBox.text!, channelDescription: channelDescriptionBox.text, users: [userNameBox.text ?? "", (Auth.auth().currentUser?.email)!])
            else{ fatalError("Unable to create Channel")}
        
        collection.addDocument(data: newChannel.dictionary)
        channelNameBox.text = ""
        channelDescriptionBox.text = ""
        userNameBox.text = ""
        
        //segue back to channel list
    }
    
}

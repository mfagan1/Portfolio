//
//  MessageTableViewCell.swift
//  ZwaxMessenger

import UIKit
import FirebaseFirestore
import Firebase

class MessageTableViewCell: UITableViewCell {
    
    @IBOutlet var senderEmailLabel: UILabel!
    @IBOutlet var messageLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
}

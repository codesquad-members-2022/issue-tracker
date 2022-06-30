//
//  FilterTableViewCell.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/29.
//

import UIKit

class FilterTableViewCell: UITableViewCell {
    
    @IBOutlet weak var titleLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    func configure(with text: String) {
        titleLabel.text = text
    }
}

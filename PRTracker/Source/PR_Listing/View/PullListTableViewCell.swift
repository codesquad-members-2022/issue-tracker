//
//  PullListTableViewCell.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/17.
//

import UIKit

class PullListTableViewCell: UITableViewCell {

    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var content: UILabel!
    @IBOutlet weak var projectName: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    func configure(with model: PullTableCellViewModel) {
        title.text = model.title.value
        content.text = model.content.value
        projectName.text = model.projectName.value
    }
}

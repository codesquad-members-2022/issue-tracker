//
//  IssueTableView.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/29.
//

import UIKit

class IssueTableView: UITableView, ViewBindable {
    
    var listContraints = [IssueFilterConstraint]()
    
    var vc: ViewBinding?
    
    func sendAction(_ param: Any?) { }
    
    func receive(_ responseData: Any) {
        self.reloadData()
    }
}

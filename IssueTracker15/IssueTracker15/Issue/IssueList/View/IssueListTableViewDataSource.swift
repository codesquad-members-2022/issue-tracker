//
//  IssueListTableViewDataSource.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/27.
//

import UIKit

class IssueListTableViewDataSource: NSObject, UITableViewDataSource, ViewBindable {
    
    private var getDataHandler: ((IndexPath) -> IssueDTO?)
    private var getCountHandler: (() -> Int?)
    var vc: ViewBinding?
    
    init(_ getDataHandler: @escaping (IndexPath) -> IssueDTO?, _ getCountHandler: @escaping () -> Int?) {
        self.getDataHandler = getDataHandler
        self.getCountHandler = getCountHandler
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        getCountHandler() ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard
            let data = getDataHandler(indexPath),
            let cell = tableView.dequeueReusableCell(withIdentifier: IssueListTableViewCell.reuseIdentifier, for: indexPath) as? IssueListTableViewCell
        else {
            return UITableViewCell()
        }
        
        cell.dto = data
        
        if let vc = vc {
            cell.setVC(vc)
        }
        
        return cell
    }
    
    func sendAction(_ param: Any?) {
        
    }
    
    func receive(_ responseData: Any) {
        
    }
    
    func setVC(_ binding: ViewBinding) {
        self.vc = binding
    }
}

private extension UITableViewCell {
    static var reuseIdentifier: String {
        return String(describing: Self.self)
    }
}

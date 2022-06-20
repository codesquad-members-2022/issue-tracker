//
//  IssueCollectionHeaderView.swift
//  IssueTracker
//
//  Created by 이건행 on 2022/06/16.
//

import SnapKit

final class IssueCollectionHeaderView: UICollectionReusableView {
    
    static let identifier = "IssueCollectionHeaderView"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addSubview(headerTitle)
        addSubview(searchBar)
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    private var headerTitle: UILabel = {
        let headerTitle = UILabel()
        let headerTitleAttributedString = NSMutableAttributedString(string: "이슈")
        headerTitle.font = UIFont(name: "SFProDisplay-Bold", size: 34)
        headerTitle.attributedText = headerTitleAttributedString
        headerTitle.textAlignment = .left
        headerTitle.translatesAutoresizingMaskIntoConstraints = false
        return headerTitle
    }()
    
    private var searchBar: UISearchBar = {
        let searchBar = UISearchBar()
        searchBar.placeholder = "Search"
        return searchBar
    }()
    
    func setHeaderTitle(text: String?) {
        headerTitle.text = text
    }
    
    override func layoutSubviews() {
        headerTitle.snp.makeConstraints { make in
            make.top.equalTo(self)
            make.leading.equalTo(self).offset(16)
//            make.bottom.equalTo(searchBar.snp.top)
        }
        
        searchBar.snp.makeConstraints { make in
            make.top.equalTo(headerTitle.snp.bottom).offset(9)
            make.leading.equalTo(headerTitle.snp.leading)
            make.trailing.equalTo(self.snp.trailing).offset(-16)
            make.bottom.equalTo(self).offset(9)
        }
    }
}

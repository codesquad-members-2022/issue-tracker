//
//  IssueNavigationController.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import UIKit

class IssueNavigationController: UINavigationController, ViewBindable {
    
    var vc: ViewBinding?
    
    func sendAction(_ param: Any?) { }
    
    func receive(_ responseData: Any) { }
    
    func setVC(_ binding: ViewBinding) {
        self.vc = binding
    }
    
    private var filterBarButtonAction: UIAction {
        UIAction(
            title: "필터",
            image: UIImage.filterButtonImage,
            identifier: nil,
            discoverabilityTitle: "Filter Issue List",
            attributes: [],
            state: .on,
            handler: { [weak self] _ in
                guard let self = self, let binding = self.vc else { return }
                binding.inputViewEvent(self, nil)
            }
        )
    }
    
    private var selectIssueBarButtonAction: UIAction {
        UIAction(
            title: "선택",
            image: UIImage.checkButtonImage,
            identifier: nil,
            discoverabilityTitle: "Select Issue List",
            attributes: [],
            state: .on,
            handler: { [weak self] _ in
                guard let self = self, let binding = self.vc else { return }
                self.currentViewState.toggle()
                binding.inputViewEvent(self, self.currentViewState)
            }
        )
    }
    
    private(set) var currentViewState: IssueListStatus = .list {
        willSet {
            switch currentViewState {
            case .list:
                print("이슈 리스트 기본화면을 보여주세요.")
            case .selection:
                print("이슈 리스트 선택화면을 보여주세요.")
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        navigationBar.topItem?.leftBarButtonItem = UIBarButtonItem(customView: UIButton(type: .system, primaryAction: filterBarButtonAction))
        navigationBar.topItem?.rightBarButtonItem = UIBarButtonItem(customView: UIButton(type: .system, primaryAction: selectIssueBarButtonAction))
    }
    
    override func pushViewController(_ viewController: UIViewController, animated: Bool) {
        let isIssueList = viewController is IssueListViewController
        navigationItem.hidesBackButton = isIssueList
        navigationBar.topItem?.leftBarButtonItem?.customView?.isHidden = isIssueList
        navigationBar.topItem?.rightBarButtonItem?.customView?.isHidden = isIssueList
        
        super.pushViewController(viewController, animated: animated)
    }
}

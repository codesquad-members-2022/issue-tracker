//
//  CommonViewModel.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/27.
//

import Foundation

protocol CommonViewModel {
    var output: (Any?, ViewBindable) -> Void { get }
    func request(_ bindable: ViewBindable, param: Any?)
}

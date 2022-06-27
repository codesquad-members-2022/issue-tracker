//
//  ViewBinding.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/27.
//

import Foundation

protocol ViewBinding {
    func inputViewEvent(_ target: ViewBindable, _ param: Any?)
}

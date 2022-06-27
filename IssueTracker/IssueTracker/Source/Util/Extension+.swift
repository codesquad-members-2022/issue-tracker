//
//  Extension+.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/20.
//

import Foundation

extension Int {

    var isSuccess: Bool {
        return isIn(range: 200...299)
    }

    private func isIn(range: ClosedRange<Int>) -> Bool {
            return range.contains(self)
    }
}

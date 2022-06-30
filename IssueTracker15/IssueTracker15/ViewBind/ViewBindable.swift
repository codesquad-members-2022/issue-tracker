//
// Created by 백상휘 on 2022/06/14.
//

import Foundation

protocol ViewBindable: AnyObject {
    var vc: ViewBinding? { get set }
    
    func sendAction(_ param: Any?)
    func receive(_ responseData: Any)
}

extension ViewBindable {
    func setVC(_ binding: ViewBinding) {
        self.vc = binding
    }
}

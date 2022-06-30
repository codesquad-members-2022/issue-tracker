//
// Created by 백상휘 on 2022/06/14.
//

import Foundation

protocol UseCaseResponsible {
    func request(param: Any?, _ completionBlock: @escaping (Any?) -> Void)
}

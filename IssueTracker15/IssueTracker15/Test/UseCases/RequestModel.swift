//
// Created by 백상휘 on 2022/06/14.
//

import Foundation
import Alamofire

class RequestModel {
    
    static func request(_ responsible: UseCaseResponsible) -> DataResponsePublisher<TestDecodableType> {
        AF
            .request("https://www.naver.com")
            .validate()
            .publishDecodable(type: TestDecodableType.self)
    }
}

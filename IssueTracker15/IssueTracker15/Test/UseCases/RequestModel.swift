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
    
    static func networkRequest(urgency: RequestUrgency? = nil) -> DataResponsePublisher<TestDecodableType>? {
        // 이전 프로젝트에서 사용한 REST-API를 이용하여 기능 테스트 중입니다.
        guard let url = URL(string: "https://public.codesquad.kr/jk/boostcamp/starbuckst-loading.json") else { return nil }
        
        var request = URLRequest(url: url)
        request.method = .get
        
        return Repository
            .shared
            .getNetworkService(resultType: TestDecodableType.self)?
            .request(request, urgency: .urgent)
    }
}

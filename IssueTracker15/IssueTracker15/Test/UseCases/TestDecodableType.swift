//
// Created by 백상휘 on 2022/06/14.
//

import Foundation

struct TestDecodableType: Decodable {
    let title: String
    let range: String
    let target: String
    let description: String
    let eventProducts: String
    
    enum CodingKeys: String, CodingKey {
      case title
      case range
      case target
      case description
      case eventProducts = "event-products"
    }
}

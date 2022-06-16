//
//  ServiceLayer.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/15.
//

import Foundation
import Combine

/// Service 추상화를 위한 프로토콜. 현재 사용하지 않음.
protocol ServiceLayer {
    // TODO: - Service 추상화 진행
    associatedtype DataResult
    func requestFromUseCase(type: ServiceType) -> AnyPublisher<DataResult, Error>?
}

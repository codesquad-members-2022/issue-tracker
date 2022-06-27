//
//  KeyChainStub.swift
//  PRTrackerTests
//
//  Created by Bumgeun Song on 2022/06/20.
//

import Foundation

struct KeyChainSuccessStub: KeyChainService {
    func save(_ string: String, service: String, account: String) {
        Log.info("Saved \(string) with \(service), \(account) in KeyChain")
    }
    
    func load(service: String, account: String) -> String? {
        return Secrets.accessToken
    }
}

struct KeyChainFailureStub: KeyChainService {
    func save(_ string: String, service: String, account: String) {
        Log.info("Saved \(string) with \(service), \(account) in KeyChain")
    }
    
    func load(service: String, account: String) -> String? {
        return nil
    }
}

class KeyChainSaveStub: KeyChainService {
    
    private var saved = [(string: String, service: String, account: String)]()
    
    func save(_ string: String, service: String, account: String) {
        saved.append((string, service, account))
    }
    
    func load(service: String, account: String) -> String? {
        return Secrets.accessToken
    }
}

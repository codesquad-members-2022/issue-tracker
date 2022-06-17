//
//  KeyChainManger.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/15.
//

import Foundation

protocol KeyChainService {
    func save(_ string: String, service: String, account: String)
    func load(service: String, account: String) -> String?
}

struct KeyChainManager: KeyChainService {
    
    func save(_ string: String, service: String, account: String) {
        let data = Data(string.utf8)
        save(data, service: service, account: account)
    }
    
    private func save(_ data: Data, service: String, account: String) {
        
        // Create query
        let query = [
            kSecValueData: data,
            kSecClass: kSecClassGenericPassword,
            kSecAttrService: service,
            kSecAttrAccount: account
        ] as CFDictionary
        
        // Add data in query to keychain
        let status = SecItemAdd(query, nil)
        
        if status == errSecSuccess {
            return
        }
        
        if status == errSecDuplicateItem {
            update(data, service: service, account: account)
            return
        }
        
        Log.error("KeyChain Error: \(status)")
    }
    
    private func update(_ data: Data, service: String, account: String) {
        let query = [
            kSecClass: kSecClassGenericPassword,
            kSecAttrService: service,
            kSecAttrAccount: account
        ] as CFDictionary
        
        let attributeToUpdate = [kSecValueData: data] as CFDictionary
        
        SecItemUpdate(query, attributeToUpdate)
    }
    
    
    func load(service: String, account: String) -> String? {
        let query = [
            kSecClass: kSecClassGenericPassword,
            kSecAttrService: service,
            kSecAttrAccount: account,
            kSecReturnData: true
        ] as CFDictionary
        
        var result: AnyObject?
        SecItemCopyMatching(query, &result)
        guard let resultData = result as? Data else { return nil }
        return String(data: resultData, encoding: .utf8)
    }
}

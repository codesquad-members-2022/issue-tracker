# Shell에 저장된 환경 변수를 불러와서, Secrets.swift 파일을 생성

file_content = <<-CREDS_FILE_STRING
// This file will be created at build phase with environment variables.

struct Secrets {
    // ENV['CLIENT_ID_GITHUB']
    static let clientId = "#{ENV['CLIENT_ID_GITHUB']}"
    // ENV['CLIENT_SECRET_GITHUB']
    static let clientSecret = "#{ENV['CLIENT_SECRET_GITHUB']}"
    // ENV['ACCESS_TOKEN_GITHUB']
    static let accessToken = "#{ENV['ACCESS_TOKEN_GITHUB']}"
}
CREDS_FILE_STRING

file = File.new("PRTracker/Supporting/Secrets.swift", "w")
file.puts(file_content)
file.close
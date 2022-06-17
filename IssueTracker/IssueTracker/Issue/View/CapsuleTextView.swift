import UIKit

extension UIColor {
    convenience init(hexString: String, alpha: CGFloat = 1.0) {
        let hexString: String = hexString.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
        let scanner = Scanner(string: hexString)
        if (hexString.hasPrefix("#")) {
            scanner.scanLocation = 1
        }
        var color: UInt32 = 0
        scanner.scanHexInt32(&color)
        let mask = 0x000000FF
        let r = Int(color >> 16) & mask
        let g = Int(color >> 8) & mask
        let b = Int(color) & mask
        let red   = CGFloat(r) / 255.0
        let green = CGFloat(g) / 255.0
        let blue  = CGFloat(b) / 255.0
        self.init(red:red, green:green, blue:blue, alpha:alpha)
    }
    func toHexString() -> String {
        var r:CGFloat = 0
        var g:CGFloat = 0
        var b:CGFloat = 0
        var a:CGFloat = 0
        getRed(&r, green: &g, blue: &b, alpha: &a)
        let rgb:Int = (Int)(r*255)<<16 | (Int)(g*255)<<8 | (Int)(b*255)<<0
        return String(format:"#%06x", rgb)
    }
}

final class CapsuleTextView: UIView {
    
    convenience init(title: String, hexColor: String) {
        self.init(frame: .zero)
        print("hexcolor=", hexColor)
        self.backgroundColor = UIColor(hexString: "#\(hexColor)")
        titleLabel.text = title
        setupViews()
    }
    
    override func draw(_ rect: CGRect) {
        super.draw(rect)
        layer.cornerRadius = rect.height * 0.5
        clipsToBounds = true
    }
    
    func updateView(title: String) {
        self.titleLabel.text = title
    }
    
    private func setupViews() {
        addSubview(titleLabel)
        self.snp.makeConstraints { make in
            make.edges.equalTo(titleLabel).inset(UIEdgeInsets(top: -4, left: -6, bottom: -4, right: -6))
        }
    }
    
    private let titleLabel: UILabel = {
       let label = UILabel()
        label.text = "label"
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
}

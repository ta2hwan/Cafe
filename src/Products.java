import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Products {
    String name; //상품명
    int price;

    public Products(String name, int price){
        this.name = name;
        this.price = price;
    }


    private static ImageIcon resizeImageIcon(String imagePath, int width, int height){
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    // Product 버튼을 만드는 함수
    public static JButton createProductButton(String name, int price, String imagePath, OrderList orderList) {
        ImageIcon productImage = resizeImageIcon(imagePath, 150, 130);
        JButton productButton = new JButton(name + " (" + price + "원)", productImage);
        productButton.setPreferredSize(new Dimension(150, 150));
        productButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        productButton.setHorizontalTextPosition(SwingConstants.CENTER); // 맨 아래 가운데에 텍스트 배치
        productButton.addActionListener(new ActionListener() {          // 버튼을 누르면 주문 리스트에 상품 정보 추가
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null,
                        name + "을(를) 리스트에 추가하시겠습니까?",
                        "상품 추가 확인",
                        JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    orderList.addItem(new Products(name, price));
                    JOptionPane.showMessageDialog(null, name + "이(가) 리스트에 추가되었습니다.");
                }
            }
        });
        return productButton;
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Products {
    String name; //상품명
    int price;

    public Products(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String toString(){
        return ("상품명 : " + name + "\t가격 : " + price + "\n");
    }

    private static ImageIcon resizeImageIcon(String imagePath, int width, int height){
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public static JButton createProductButton(String name, int price, String imagePath) {
        ImageIcon productImage = resizeImageIcon(imagePath, 150, 130);
        JButton productButton = new JButton(name + " (" + price + "원)", productImage);
        productButton.setPreferredSize(new Dimension(150, 150));
        productButton.setHorizontalTextPosition(SwingConstants.CENTER);
        productButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        productButton.setToolTipText(name + ": " + price + "원");
        return productButton;
    }
}

public class Screen extends JFrame {
    private Container c = getContentPane();
    private JPanel coffeePanel;
    private JPanel smoothiePanel;
    private JPanel adePanel;
    private JPanel dessertPanel;

    private Vector<JButton> coffeeMenu;
    private Vector<JButton> smoothieMenu;
    private Vector<JButton> adeMenu;
    private Vector<JButton> dessertMenu;

    // 초기화면
    public Screen() {
        setTitle("카페 키오스크 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        c.setLayout(new BorderLayout());

        coffeeMenu = new Vector<>();
        smoothieMenu = new Vector<>();
        adeMenu = new Vector<>();
        dessertMenu = new Vector<>();



        // 각각의 패널을 초기화합니다.
        coffeePanel = createCoffeePanel();
        smoothiePanel = createSmoothiePanel();
        adePanel = createAdePanel();
        dessertPanel = createDessertPanel();

        // 메뉴를 추가합니다.
        menu();

        // 초기화면에서는 커피 패널이 보이도록 설정합니다.
        c.add(coffeePanel, BorderLayout.CENTER);

        setSize(700, 700);
        setVisible(true);
    }

    void menu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(1, 4));

        JMenuItem[] menuItem = new JMenuItem[4];
        String[] menuItemName = { "커피", "스무디", "에이드", "디저트" };

        for (int i = 0; i < menuItem.length; i++) {
            menuItem[i] = new JMenuItem(menuItemName[i]);
            menuItem[i].setPreferredSize(new Dimension(menuItem[i].getPreferredSize().width, 70));
            menuItem[i].addActionListener(new MenuActionListener());
            menuPanel.add(menuItem[i]);
        }

        c.add(menuPanel, BorderLayout.NORTH);
    }

    private class MenuActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            String selectedMenu = menuItem.getText();

            switch (selectedMenu) {
                case "커피":
                    showPanel(coffeePanel);
                    break;
                case "스무디":
                    showPanel(smoothiePanel);
                    break;
                case "에이드":
                    showPanel(adePanel);
                    break;
                case "디저트":
                    showPanel(dessertPanel);
                    break;
            }

            JOptionPane.showMessageDialog(null, selectedMenu + " 메뉴를 선택하셨습니다.");
        }
    }

    private JPanel createCoffeePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        panel.setVisible(true);

        coffeeMenu.add(Products.createProductButton("아메리카노", 3000, "images/americano.jpg"));
        coffeeMenu.add(Products.createProductButton("카페라떼", 3000, "images/cafe_latte.jpg"));

        for(JButton coffee : coffeeMenu){
            panel.add(coffee);
        }

        panel.setBackground(Color.WHITE); // 예시로 패널의 배경색을 흰색으로 설정합니다.
        return panel;
    }

    private JPanel createSmoothiePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        panel.setVisible(true);

        smoothieMenu.add(Products.createProductButton("망고스무디", 3000, "images/mango_smoothie.jpg"));
        smoothieMenu.add(Products.createProductButton("딸기스무디", 3000, "images/strawberry_smoothie.jpg"));

        for(JButton smoothie : smoothieMenu){
            panel.add(smoothie);
        }

        panel.setBackground(Color.WHITE); // 예시로 패널의 배경색을 흰색으로 설정합니다.
        return panel;
    }

    private JPanel createAdePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        panel.setVisible(true);

        adeMenu.add(Products.createProductButton("블루레몬에이드", 3000, "images/blueLemon_ade.jpg"));
        adeMenu.add(Products.createProductButton("자몽에이드", 3000, "images/grapeFruit_ade.jpg"));

        for(JButton ade : adeMenu){
            panel.add(ade);
        }

        panel.setBackground(Color.WHITE); // 예시로 패널의 배경색을 흰색으로 설정합니다.
        return panel;
    }

    private JPanel createDessertPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        panel.setVisible(true);

        smoothieMenu.add(Products.createProductButton("망고스무디", 3000, "images/mango_smoothie.jpg"));
        smoothieMenu.add(Products.createProductButton("딸기스무디", 3000, "images/strawberry_smoothie.jpg"));

        for(JButton smoothie : smoothieMenu){
            panel.add(smoothie);
        }

        panel.setBackground(Color.WHITE); // 예시로 패널의 배경색을 흰색으로 설정합니다.
        return panel;
    }

    private void showPanel(JPanel panel) {
        // 기존에 추가된 모든 패널을 제거합니다.
        c.remove(coffeePanel);
        c.remove(smoothiePanel);
        c.remove(adePanel);
        c.remove(dessertPanel);

        // 전달받은 패널을 추가합니다.
        c.add(panel, BorderLayout.CENTER);

        // 화면을 다시 그립니다.
        c.revalidate();
        c.repaint();
    }

    public static void main(String[] args) {
        new Screen();
    }
}

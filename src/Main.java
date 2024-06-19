import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main extends JFrame {
    private Container c = getContentPane();
    private JPanel coffeePanel;
    private JPanel smoothiePanel;
    private JPanel adePanel;
    private JPanel dessertPanel;

    private Vector<JButton> coffeeMenu;
    private Vector<JButton> smoothieMenu;
    private Vector<JButton> adeMenu;
    private Vector<JButton> dessertMenu;

    private OrderList orderList;

    // 초기화면
    public Main() {
        setTitle("카페 키오스크 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        c.setLayout(new BorderLayout());

        coffeeMenu = new Vector<>();
        smoothieMenu = new Vector<>();
        adeMenu = new Vector<>();
        dessertMenu = new Vector<>();

        orderList = new OrderList();
        c.add(orderList, BorderLayout.SOUTH);


        // 4개 패널 생성, 안 보이는 상태
        coffeePanel = createCoffeePanel();
        smoothiePanel = createSmoothiePanel();
        adePanel = createAdePanel();
        dessertPanel = createDessertPanel();

        // 메뉴 추가
        menu();

        // 초기 화면은 커피패널로 설정
        c.add(coffeePanel, BorderLayout.CENTER);

        setSize(900, 900);
        setVisible(true);
    }

    void menu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(1, 4));

        JMenuItem[] menuItem = new JMenuItem[4];
        String[] menuItemName = {"커피", "스무디", "에이드", "디저트"};

        for (int i = 0; i < menuItem.length; i++) {
            menuItem[i] = new JMenuItem(menuItemName[i]);
            menuItem[i].setPreferredSize(new Dimension(menuItem[i].getPreferredSize().width, 70));
            menuItem[i].addActionListener(new MenuActionListener());
            menuPanel.add(menuItem[i]);
            menuItem[i].setRolloverEnabled(true);
            // 메뉴에 마우스를 올리면 LIGHT_GRAY로 색 변경
            menuItem[i].addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    JMenuItem item = (JMenuItem) e.getSource();
                    item.setBackground(Color.LIGHT_GRAY);
                }

                public void mouseExited(MouseEvent e) {
                    JMenuItem item = (JMenuItem) e.getSource();
                    item.setBackground(null);
                }
            });
        }
        c.add(menuPanel, BorderLayout.NORTH);
    }

    // 메뉴를 마우스 클릭하면 showPanel 메소드 실행
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

    // 커피 패널 생성
    private JPanel createCoffeePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        panel.setVisible(true);

        coffeeMenu.add(Products.createProductButton("아메리카노", 2000, "images/americano.jpg", orderList));
        coffeeMenu.add(Products.createProductButton("디카페인커피", 3500, "images/decaf_coffee.jpg", orderList));
        coffeeMenu.add(Products.createProductButton("카페라떼", 3500, "images/cafe_latte.jpg", orderList));
        coffeeMenu.add(Products.createProductButton("에스프레소", 3500, "images/esspresso.jpg", orderList));

        for (JButton coffee : coffeeMenu) {
            panel.add(coffee);
        }

        panel.setBackground(new Color(245, 245, 220));

        return panel;
    }

    // 스무디 패널 생성
    private JPanel createSmoothiePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        panel.setVisible(true);

        smoothieMenu.add(Products.createProductButton("망고스무디", 5000, "images/mango_smoothie.jpg", orderList));
        smoothieMenu.add(Products.createProductButton("딸기스무디", 5000, "images/strawberry_smoothie.jpg", orderList));
        smoothieMenu.add(Products.createProductButton("녹차스무디", 5000, "images/greenTea_smoothie.jpg", orderList));
        smoothieMenu.add(Products.createProductButton("감자스무디", 5000, "images/potato_smoothie.jpg", orderList));

        for (JButton smoothie : smoothieMenu) {
            panel.add(smoothie);
        }
        
        panel.setBackground(new Color(245, 245, 220));
        return panel;
    }

    // 에이드 패널 생성
    private JPanel createAdePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        panel.setVisible(true);

        adeMenu.add(Products.createProductButton("블루레몬에이드", 3000, "images/blueLemon_ade.jpg", orderList));
        adeMenu.add(Products.createProductButton("자몽에이드", 3000, "images/grapeFruit_ade.jpg", orderList));
        adeMenu.add(Products.createProductButton("오렌지에이드", 3000, "images/orange_ade.jpg", orderList));
        adeMenu.add(Products.createProductButton("자몽에이드", 3000, "images/pineapple_ade.jpg", orderList));

        for (JButton ade : adeMenu) {
            panel.add(ade);
        }

        panel.setBackground(new Color(245, 245, 220));
        return panel;
    }

    //디저트 패널 생성
    private JPanel createDessertPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        panel.setVisible(true);

        dessertMenu.add(Products.createProductButton("와플    ", 3000, "images/waffle.jpg", orderList));
        dessertMenu.add(Products.createProductButton("아이스크림", 3000, "images/icecream.jpg", orderList));
        dessertMenu.add(Products.createProductButton("도넛    ", 3000, "images/doughnut.jpg", orderList));
        dessertMenu.add(Products.createProductButton("크로아상", 3000, "images/croissant.jpg", orderList));

        for (JButton dessert : dessertMenu) {
            panel.add(dessert);
        }

        panel.setBackground(new Color(245, 245, 220));
        return panel;
    }

    //매개변수로 받은 패널 보여주는 메소드.
    private void showPanel(JPanel panel) {
        // 기존에 추가된 모든 패널 제거
        c.remove(coffeePanel);
        c.remove(smoothiePanel);
        c.remove(adePanel);
        c.remove(dessertPanel);

        // 전달받은 패널을 추가
        c.add(panel, BorderLayout.CENTER);

        // 화면 다시 그리기
        c.revalidate();
        c.repaint();
    }

    public static void main(String[] args) {
        new Main();
    }
};
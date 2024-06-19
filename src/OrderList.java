import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderList extends JPanel {
    private JPanel orderPanel;
    private ArrayList<Products> items;
    private JButton purchaseButton;

    public OrderList() {
        setLayout(new BorderLayout());
        items = new ArrayList<>();

        // 주문 패널 생성
        orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS)); // BoxLayout을 사용하여 수직 정렬
        JScrollPane scrollPane = new JScrollPane(orderPanel); //스크롤 생성
        scrollPane.setPreferredSize(new Dimension(400, 200)); // 주문 리스트 패널 크기 설정
        add(scrollPane, BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder("주문 리스트"));

        purchaseButton = new JButton("구매하기");
        purchaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                purchaseItems();  // 상품 구매 메소드
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(purchaseButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addItem(Products product) {
        items.add(product);
        updateOrderPanel();
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            updateOrderPanel();
        }
    }

    private void updateOrderPanel() {
        orderPanel.removeAll();
        for (int i = 0; i < items.size(); i++) {
            Products product = items.get(i);
            JPanel itemPanel = createItemPanel(product, i);
            orderPanel.add(itemPanel);
        }
        // 남는 부분을 공백으로 채움
        int emptySpace = (5 - items.size()); // 5개의 패널을 가정
        for (int i = 0; i < emptySpace; i++) {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setPreferredSize(new Dimension(900, 40));
            orderPanel.add(emptyPanel);
        }
        orderPanel.revalidate();
        orderPanel.repaint();
    }

    private JPanel createItemPanel(Products product, int index) {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setPreferredSize(new Dimension(400, 40)); // 상품 패널의 크기 설정

        // 상품명과 가격 라벨 설정
        JLabel nameLabel = new JLabel("상품명: " + product.name);
        JLabel priceLabel = new JLabel("가격: " + product.price + "원");

        // 삭제 버튼 설정
        JButton deleteButton = new JButton("삭제");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeItem(index);
            }
        });

        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(priceLabel, BorderLayout.CENTER);
        panel.add(deleteButton, BorderLayout.EAST);

        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // 테두리 설정

        return panel;
    }

    private void purchaseItems() {
        int totalPrice = 0;

        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(null, "구매할 상품이 없습니다.");
            return;
        }

        int response = JOptionPane.showConfirmDialog(
                null, "구매하시겠습니까?", "구매 확인", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            for (Products product : items) {  // 상품 총 가격 결제
                totalPrice += product.price;
            }
            JOptionPane.showMessageDialog(null, totalPrice + "원 결제하였습니다.");

            items.clear();
            updateOrderPanel();
        } else {
            JOptionPane.showMessageDialog(null, "구매가 취소되었습니다.");
        }
    }
}
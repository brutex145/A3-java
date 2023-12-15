import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import java.awt.geom.RoundRectangle2D;

public class BookRankerLogin extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public BookRankerLogin() {
        setTitle("BookRanker - Login");
        setSize(500, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        RoundedPanel panel = createLoginPanel();

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    private RoundedPanel createLoginPanel() {
        RoundedPanel panel = new RoundedPanel(30);
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        JLabel usernameLabel = new JLabel("Login");
        JLabel passwordLabel = new JLabel("Senha");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = createLoginButton();

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        return panel;
    }

    private JButton createLoginButton() {
        JButton loginButton = new JButton("Entrar");
        loginButton.setForeground(Color.BLACK);
        loginButton.setBackground(new Color(173, 216, 230));
        loginButton.setPreferredSize(new Dimension(80, 30));
        loginButton.addActionListener(this);
        return loginButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BookRankerLogin();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("Universidade São Judas Tadeu") && password.equals("A3")) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
        } else {
            JOptionPane.showMessageDialog(this, "Login falhou. Tente novamente.");
        }
    }
}

class RoundedPanel extends JPanel {
    private Shape shape;

    public RoundedPanel(int radius) {
        super();
        setOpaque(false);
        shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        ((Graphics2D) g).fill(shape);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        ((Graphics2D) g).draw(shape);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30);
        }
        return shape.contains(x, y);
    }
}
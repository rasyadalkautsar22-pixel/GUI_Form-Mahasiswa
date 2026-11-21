import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GuiLatihan extends JFrame {
    JTextField tfNama = new JTextField(12), tfNim = new JTextField(12);
    JComboBox<String> cbProdi = new JComboBox<>(new String[] {
        "Informatika", "Sistem Informasi", "Teknik Komputer"
    });
    JTextArea taHasil = new JTextArea(6, 25);
    JLabel lblStatus = new JLabel("Siap.");

    public GuiLatihan() {
        super("Form Mahasiswa");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));                    // Layout 1

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5)); // Layout 2
        form.add(new JLabel("Nama"));
        form.add(tfNama);
        form.add(new JLabel("NIM (angka)"));
        form.add(tfNim);
        form.add(new JLabel("Program Studi"));
        form.add(cbProdi);

        JButton btnSimpan = new JButton("Simpan");
        JButton btnReset = new JButton("Reset");
        JPanel tombol = new JPanel(new FlowLayout());         // Layout 3
        tombol.add(btnSimpan);
        tombol.add(btnReset);

        taHasil.setEditable(false);
        add(form, BorderLayout.NORTH);
        add(new JScrollPane(taHasil), BorderLayout.CENTER);
        JPanel bawah = new JPanel(new BorderLayout());
        bawah.add(tombol, BorderLayout.NORTH);
        bawah.add(lblStatus, BorderLayout.SOUTH);
        add(bawah, BorderLayout.SOUTH);

        btnSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simpan();
            }
        });
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        tfNim.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                    lblStatus.setText("NIM hanya angka!");
                    lblStatus.setForeground(Color.RED);
                }
            }
        });
        lblStatus.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                lblStatus.setForeground(Color.BLUE);
            }

            public void mouseExited(MouseEvent e) {
                lblStatus.setForeground(Color.BLACK);
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int pilih = JOptionPane.showConfirmDialog(null, "Yakin keluar?");
                if (pilih == JOptionPane.YES_OPTION) dispose();
            }
        });
        pack();
        setLocationRelativeTo(null);
    }

    void simpan() {
        if (tfNama.getText().isEmpty() || tfNim.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama dan NIM wajib diisi!");
            return;
        }
        taHasil.append(tfNim.getText() + " | " + tfNama.getText()
            + " | " + cbProdi.getSelectedItem() + "\n");
        lblStatus.setText("Data disimpan.");
        tfNama.setText("");
        tfNim.setText("");
    }

    void reset() {
        tfNama.setText("");
        tfNim.setText("");
        cbProdi.setSelectedIndex(0);
        taHasil.setText("");
        lblStatus.setText("Siap.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuiLatihan().setVisible(true));
    }
}

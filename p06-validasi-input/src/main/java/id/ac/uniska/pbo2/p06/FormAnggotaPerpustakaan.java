/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package id.ac.uniska.pbo2.p06;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class FormAnggotaPerpustakaan extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormAnggotaPerpustakaan.class.getName());

    /**
     * Creates new form FormAnggotaPerpustakaan
     */
    public FormAnggotaPerpustakaan() {
        initComponents();
        judulLabel.putClientProperty("FlatLaf.styleClass", "h2");
        keteranganLabel.putClientProperty("FlatLaf.style", "foreground: $Label.disabledForeground");
        dataAnggotaLabel.putClientProperty("FlatLaf.styleClass", "h4");
        kontakLabel.putClientProperty("FlatLaf.styleClass", "h4");
        digitLabel.putClientProperty("FlatLaf.styleClass", "small");
        npmField.putClientProperty("JTextField.placeholderText", "Contoh: 2410010123");
        emailField.putClientProperty("JTextField.placeholderText", "nama@contoh.com");
        hpField.putClientProperty("JTextField.placeholderText", "08xxxxxxxxxx");

        for (JLabel pesan : List.of(npmPesanLabel, namaPesanLabel, prodiPesanLabel,
                emailPesanLabel, hpPesanLabel)) {
            pesan.putClientProperty("FlatLaf.styleClass", "small");
            pesan.setForeground(warnaKesalahan());
        }   
        statusLabel.setForeground(warnaKesalahan());

        getRootPane().setDefaultButton(daftarButton);
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
    }

    private boolean periksaNpm() {
    return tandai(npmField, npmPesanLabel, Validator.npm(npmField.getText()));
    }

    private boolean periksaNama() {
        return tandai(namaField, namaPesanLabel, Validator.nama(namaField.getText()));
    }

    private boolean periksaProdi() {
        String kesalahan = null;
        if (prodiCombo.getSelectedIndex() == 0) {
            kesalahan = "Pilih salah satu program studi";
        }
        return tandai(prodiCombo, prodiPesanLabel, kesalahan);
    }

    private boolean periksaEmail() {
        return tandai(emailField, emailPesanLabel, Validator.email(emailField.getText()));
    }

    private boolean periksaHp() {
        return tandai(hpField, hpPesanLabel, Validator.nomorHp(hpField.getText()));
    }

    private boolean tandai(JComponent isian, JLabel pesan, String kesalahan) {
        isian.putClientProperty("JComponent.outline", (kesalahan == null) ? null : "error");
        pesan.setText((kesalahan == null) ? " " : kesalahan);
        return kesalahan == null;
    }

    private void daftar() {
        // Semua isian diperiksa agar pengguna melihat seluruh kesalahan sekaligus
        List<JComponent> salah = new ArrayList<>();
        if (!periksaNpm()) {
            salah.add(npmField);
        }
        if (!periksaNama()) {
            salah.add(namaField);
        }
        if (!periksaProdi()) {
            salah.add(prodiCombo);
        }
        if (!periksaEmail()) {
            salah.add(emailField);
        }
        if (!periksaHp()) {
            salah.add(hpField);
        }
        if (!salah.isEmpty()) {
            statusLabel.setText(salah.size() + " isian perlu diperbaiki");
            salah.get(0).requestFocusInWindow(); // arahkan pengguna ke kesalahan pertama
            return;
        }
        statusLabel.setText(" ");
        String ringkasan = "Pendaftaran berhasil."
                + "\n\nNPM: " + npmField.getText().trim()
                + "\nNama lengkap: " + namaField.getText().trim()
                + "\nProgram studi: " + prodiCombo.getSelectedItem()
                + "\nJenis anggota: " + jenisAnggota()
                + "\nEmail: " + emailField.getText().trim()
                + "\nNomor HP: " + hpField.getText().trim()
                + "\nAlamat: " + alamatArea.getText().trim();
        JOptionPane.showMessageDialog(this, ringkasan, "Pendaftaran Berhasil",
                JOptionPane.INFORMATION_MESSAGE);
        kosongkan();
    }

    private String jenisAnggota() {
        if (dosenRadio.isSelected()) {
            return "Dosen";
        }
        if (umumRadio.isSelected()) {
            return "Umum";
        }
        return "Mahasiswa";
    }

    private void batal() {
        // Aksi yang menghapus data meminta konfirmasi terlebih dahulu
        if (adaIsian()) {
            int jawab = JOptionPane.showConfirmDialog(this,
                    "Data yang sudah diisi akan dihapus. Lanjutkan?", "Konfirmasi",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (jawab != JOptionPane.YES_OPTION) {
                return;
            }
        }
        kosongkan();
    }

    private boolean adaIsian() {
        return !npmField.getText().isBlank() || !namaField.getText().isBlank()
                || !emailField.getText().isBlank() || !hpField.getText().isBlank()
                || !alamatArea.getText().isBlank();
    }

    private void kosongkan() {
        for (JTextField isian : List.of(npmField, namaField, emailField, hpField)) {
            isian.setText("");
        }
        alamatArea.setText("");
        prodiCombo.setSelectedIndex(0);
        mahasiswaRadio.setSelected(true);
        setujuCheck.setSelected(false);
        digitLabel.setText("0 dari 10 digit");
        tandai(npmField, npmPesanLabel, null);
        tandai(namaField, namaPesanLabel, null);
        tandai(prodiCombo, prodiPesanLabel, null);
        tandai(emailField, emailPesanLabel, null);
        tandai(hpField, hpPesanLabel, null);
        statusLabel.setText(" ");
        npmField.requestFocusInWindow();
    }

    static Color warnaKesalahan() {
        return FlatLaf.isLafDark() ? new Color(0xFF8A80) : new Color(0xC62828);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jenisAnggotaGroup = new javax.swing.ButtonGroup();
        headerPanel = new javax.swing.JPanel();
        judulLabel = new javax.swing.JLabel();
        keteranganLabel = new javax.swing.JLabel();
        isiPanel = new javax.swing.JPanel();
        formPanel = new javax.swing.JPanel();
        dataAnggotaLabel = new javax.swing.JLabel();
        npmLabel = new javax.swing.JLabel();
        npmField = new javax.swing.JTextField();
        namaLabel = new javax.swing.JLabel();
        namaField = new javax.swing.JTextField();
        prodiLabel = new javax.swing.JLabel();
        prodiCombo = new javax.swing.JComboBox<>();
        jenisLabel = new javax.swing.JLabel();
        jenisPanel = new javax.swing.JPanel();
        mahasiswaRadio = new javax.swing.JRadioButton();
        dosenRadio = new javax.swing.JRadioButton();
        umumRadio = new javax.swing.JRadioButton();
        kontakLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        hpLabel = new javax.swing.JLabel();
        hpField = new javax.swing.JTextField();
        alamatLabel = new javax.swing.JLabel();
        alamatScroll = new javax.swing.JScrollPane();
        alamatArea = new javax.swing.JTextArea();
        setujuCheck = new javax.swing.JCheckBox();
        digitLabel = new javax.swing.JLabel();
        npmPesanLabel = new javax.swing.JLabel();
        namaPesanLabel = new javax.swing.JLabel();
        prodiPesanLabel = new javax.swing.JLabel();
        emailPesanLabel = new javax.swing.JLabel();
        hpPesanLabel = new javax.swing.JLabel();
        bawahPanel = new javax.swing.JPanel();
        tombolPanel = new javax.swing.JPanel();
        batalButton = new javax.swing.JButton();
        daftarButton = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Form Anggota Perpustakaan");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        headerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 8, 16));
        headerPanel.setLayout(new javax.swing.BoxLayout(headerPanel, javax.swing.BoxLayout.Y_AXIS));

        judulLabel.setText("Form Anggota Perpustakaan");
        headerPanel.add(judulLabel);

        keteranganLabel.setText("Lengkapi data berikut untuk mendaftar sebagai anggota perpustakaan.");
        headerPanel.add(keteranganLabel);

        getContentPane().add(headerPanel, java.awt.BorderLayout.NORTH);

        isiPanel.setLayout(new java.awt.BorderLayout());

        formPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 16, 8, 16));
        formPanel.setLayout(new java.awt.GridBagLayout());

        dataAnggotaLabel.setText("Data Anggota");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        formPanel.add(dataAnggotaLabel, gridBagConstraints);

        npmLabel.setDisplayedMnemonic('P');
        npmLabel.setLabelFor(npmField);
        npmLabel.setText("NPM");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 12);
        formPanel.add(npmLabel, gridBagConstraints);

        npmField.setToolTipText("Hanya angka, tepat 10 digit");
        npmField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                npmFieldFocusLost(evt);
            }
        });
        npmField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                npmFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                npmFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        formPanel.add(npmField, gridBagConstraints);

        namaLabel.setDisplayedMnemonic('N');
        namaLabel.setLabelFor(namaField);
        namaLabel.setText("Nama lengkap");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 12);
        formPanel.add(namaLabel, gridBagConstraints);

        namaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                namaFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        formPanel.add(namaField, gridBagConstraints);

        prodiLabel.setDisplayedMnemonic('R');
        prodiLabel.setLabelFor(prodiCombo);
        prodiLabel.setText("Program studi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 12);
        formPanel.add(prodiLabel, gridBagConstraints);

        prodiCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih program studi", "Teknik Informatika", "Sistem Informasi", "Manajemen Informatika" }));
        prodiCombo.addItemListener(this::prodiComboItemStateChanged);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        formPanel.add(prodiCombo, gridBagConstraints);

        jenisLabel.setDisplayedMnemonic('J');
        jenisLabel.setLabelFor(mahasiswaRadio);
        jenisLabel.setText("Jenis anggota");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 12);
        formPanel.add(jenisLabel, gridBagConstraints);

        jenisPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jenisAnggotaGroup.add(mahasiswaRadio);
        mahasiswaRadio.setMnemonic('H');
        mahasiswaRadio.setSelected(true);
        mahasiswaRadio.setText("Mahasiswa");
        jenisPanel.add(mahasiswaRadio);

        jenisAnggotaGroup.add(dosenRadio);
        dosenRadio.setMnemonic('D');
        dosenRadio.setText("Dosen");
        jenisPanel.add(dosenRadio);

        jenisAnggotaGroup.add(umumRadio);
        umumRadio.setMnemonic('U');
        umumRadio.setText("Umum");
        jenisPanel.add(umumRadio);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        formPanel.add(jenisPanel, gridBagConstraints);

        kontakLabel.setText("Kontak");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 6, 0);
        formPanel.add(kontakLabel, gridBagConstraints);

        emailLabel.setDisplayedMnemonic('E');
        emailLabel.setLabelFor(emailField);
        emailLabel.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 12);
        formPanel.add(emailLabel, gridBagConstraints);

        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        formPanel.add(emailField, gridBagConstraints);

        hpLabel.setDisplayedMnemonic('H');
        hpLabel.setLabelFor(hpField);
        hpLabel.setText("Nomor HP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 12);
        formPanel.add(hpLabel, gridBagConstraints);

        hpField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                hpFieldFocusLost(evt);
            }
        });
        hpField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hpFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        formPanel.add(hpField, gridBagConstraints);

        alamatLabel.setDisplayedMnemonic('L');
        alamatLabel.setLabelFor(alamatArea);
        alamatLabel.setText("Alamat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 12);
        formPanel.add(alamatLabel, gridBagConstraints);

        alamatArea.setColumns(20);
        alamatArea.setLineWrap(true);
        alamatArea.setRows(4);
        alamatScroll.setViewportView(alamatArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        formPanel.add(alamatScroll, gridBagConstraints);

        setujuCheck.setMnemonic('T');
        setujuCheck.setText("Setuju dengan tata tertib perpustakaan");
        setujuCheck.addItemListener(this::setujuCheckItemStateChanged);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 4, 0);
        formPanel.add(setujuCheck, gridBagConstraints);

        digitLabel.setText("0 dari 10 digit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        formPanel.add(digitLabel, gridBagConstraints);

        npmPesanLabel.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 0);
        formPanel.add(npmPesanLabel, gridBagConstraints);

        namaPesanLabel.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 0);
        formPanel.add(namaPesanLabel, gridBagConstraints);

        prodiPesanLabel.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 0);
        formPanel.add(prodiPesanLabel, gridBagConstraints);

        emailPesanLabel.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 0);
        formPanel.add(emailPesanLabel, gridBagConstraints);

        hpPesanLabel.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 0);
        formPanel.add(hpPesanLabel, gridBagConstraints);

        isiPanel.add(formPanel, java.awt.BorderLayout.NORTH);

        getContentPane().add(isiPanel, java.awt.BorderLayout.CENTER);

        bawahPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 16, 16, 16));
        bawahPanel.setLayout(new java.awt.BorderLayout());

        tombolPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));

        batalButton.setMnemonic('B');
        batalButton.setText("Batal");
        batalButton.addActionListener(this::batalButtonActionPerformed);
        tombolPanel.add(batalButton);

        daftarButton.setText("Daftar");
        daftarButton.setEnabled(false);
        daftarButton.addActionListener(this::daftarButtonActionPerformed);
        tombolPanel.add(daftarButton);

        bawahPanel.add(tombolPanel, java.awt.BorderLayout.EAST);

        statusLabel.setText(" ");
        bawahPanel.add(statusLabel, java.awt.BorderLayout.CENTER);

        getContentPane().add(bawahPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setujuCheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_setujuCheckItemStateChanged
        daftarButton.setEnabled(setujuCheck.isSelected());
    }//GEN-LAST:event_setujuCheckItemStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       int jawab = JOptionPane.showConfirmDialog(this,
        "Keluar dari aplikasi?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void batalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalButtonActionPerformed
        batal();
    }//GEN-LAST:event_batalButtonActionPerformed

    private void daftarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftarButtonActionPerformed
      daftar();
    }//GEN-LAST:event_daftarButtonActionPerformed

    private void npmFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_npmFieldKeyReleased
        long jumlah = npmField.getText().chars().filter(Character::isDigit).count();
        digitLabel.setText(jumlah + " dari 10 digit");
    }//GEN-LAST:event_npmFieldKeyReleased

    private void npmFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_npmFieldKeyTyped
       boolean penuh = npmField.getText().length() >= 10
            && npmField.getSelectedText() == null;
        if (!Character.isDigit(evt.getKeyChar()) || penuh) {
            evt.consume(); // karakter tidak dimasukkan ke isian
        }
    }//GEN-LAST:event_npmFieldKeyTyped

    private void hpFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hpFieldKeyTyped
       boolean penuh = hpField.getText().length() >= 13
                && hpField.getSelectedText() == null;
        if (!Character.isDigit(evt.getKeyChar()) || penuh) {
            evt.consume();
        }
    }//GEN-LAST:event_hpFieldKeyTyped

    private void npmFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_npmFieldFocusLost
       if (!npmField.getText().isBlank()) {
          periksaNpm();
        }
    }//GEN-LAST:event_npmFieldFocusLost

    private void namaFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_namaFieldFocusLost
        if (!namaField.getText().isBlank()) {
            periksaNama();
        }
    }//GEN-LAST:event_namaFieldFocusLost

    private void emailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusLost
        if (!emailField.getText().isBlank()) {
            periksaEmail();
        }
    }//GEN-LAST:event_emailFieldFocusLost

    private void hpFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hpFieldFocusLost
        if (!hpField.getText().isBlank()) {
            periksaHp();
        }
    }//GEN-LAST:event_hpFieldFocusLost

    private void prodiComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_prodiComboItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            periksaProdi();
        }
    }//GEN-LAST:event_prodiComboItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       FlatLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FormAnggotaPerpustakaan().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamatArea;
    private javax.swing.JLabel alamatLabel;
    private javax.swing.JScrollPane alamatScroll;
    private javax.swing.JButton batalButton;
    private javax.swing.JPanel bawahPanel;
    private javax.swing.JButton daftarButton;
    private javax.swing.JLabel dataAnggotaLabel;
    private javax.swing.JLabel digitLabel;
    private javax.swing.JRadioButton dosenRadio;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emailPesanLabel;
    private javax.swing.JPanel formPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JTextField hpField;
    private javax.swing.JLabel hpLabel;
    private javax.swing.JLabel hpPesanLabel;
    private javax.swing.JPanel isiPanel;
    private javax.swing.ButtonGroup jenisAnggotaGroup;
    private javax.swing.JLabel jenisLabel;
    private javax.swing.JPanel jenisPanel;
    private javax.swing.JLabel judulLabel;
    private javax.swing.JLabel keteranganLabel;
    private javax.swing.JLabel kontakLabel;
    private javax.swing.JRadioButton mahasiswaRadio;
    private javax.swing.JTextField namaField;
    private javax.swing.JLabel namaLabel;
    private javax.swing.JLabel namaPesanLabel;
    private javax.swing.JTextField npmField;
    private javax.swing.JLabel npmLabel;
    private javax.swing.JLabel npmPesanLabel;
    private javax.swing.JComboBox<String> prodiCombo;
    private javax.swing.JLabel prodiLabel;
    private javax.swing.JLabel prodiPesanLabel;
    private javax.swing.JCheckBox setujuCheck;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel tombolPanel;
    private javax.swing.JRadioButton umumRadio;
    // End of variables declaration//GEN-END:variables
}

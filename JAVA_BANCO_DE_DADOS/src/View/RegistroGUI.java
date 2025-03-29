package View;

import Controller.RegistroController;
import Dao.RegistroDao;
import Models.Registro;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistroGUI extends JFrame {
    private JTextField termoField, livroField, folhaField, nomeField, nomeGenitorField, nomeGenitoraField, dataNascimentoField, sexoField, dataRegistroField;
    private JButton registrarButton, excluirButton, listarButton;
    private JList<String> listaRegistros;
    private DefaultListModel<String> listModel;
    private RegistroController controller;
    private RegistroDao registroDao;

    public RegistroGUI() {
        controller = new RegistroController();
        registroDao = new RegistroDao();

        setTitle("Cartório");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel esquerdo (Lista de Registros)
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(64, 60, 26));

        JLabel registrosLabel = new JLabel("REGISTROS", SwingConstants.CENTER);
        registrosLabel.setForeground(Color.WHITE);
        registrosLabel.setFont(new Font("Arial", Font.BOLD, 14));

        listModel = new DefaultListModel<>();
        listaRegistros = new JList<>(listModel);
        listaRegistros.setBackground(new Color(219, 190, 45));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        listarButton = new JButton("Listar Registros");
        excluirButton = new JButton("Excluir Selecionado");

        buttonPanel.add(listarButton);
        buttonPanel.add(excluirButton);
        buttonPanel.setBackground(new Color(64, 60, 26));

        leftPanel.add(registrosLabel, BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(listaRegistros), BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Painel direito (Formulário)
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(255, 244, 166));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addFormField(rightPanel, gbc, "Livro:", livroField = new JTextField());
        addFormField(rightPanel, gbc, "Folha:", folhaField = new JTextField());
        addFormField(rightPanel, gbc, "Termo:", termoField = new JTextField());
        addFormField(rightPanel, gbc, "Data de Registro:", dataRegistroField = new JTextField());
        addFormField(rightPanel, gbc, "Nome:", nomeField = new JTextField());
        addFormField(rightPanel, gbc, "PAI:", nomeGenitorField = new JTextField());
        addFormField(rightPanel, gbc, "MÃE:", nomeGenitoraField = new JTextField());
        addFormField(rightPanel, gbc, "Data de Nascimento:", dataNascimentoField = new JTextField());
        addFormField(rightPanel, gbc, "Sexo (M, F, Outro):", sexoField = new JTextField());

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        registrarButton = new JButton("Registrar");
        registrarButton.setBackground(new Color(64, 60, 26));
        registrarButton.setForeground(Color.WHITE);
        rightPanel.add(registrarButton, gbc);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(250);
        add(splitPane, BorderLayout.CENTER);

        registrarButton.addActionListener(_ -> salvarRegistro());
        listarButton.addActionListener(_ -> listarRegistros());
        excluirButton.addActionListener(_ -> excluirRegistro());

        setVisible(true);
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField textField) {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        textField.setPreferredSize(new Dimension(200, 25));
        panel.add(textField, gbc);
    }

    private void salvarRegistro() {
        registrarButton.setEnabled(false);
        try {
            int termo = Integer.parseInt(termoField.getText());
            String livro = livroField.getText();
            int folha = Integer.parseInt(folhaField.getText());
            String nome = nomeField.getText();
            String nomeGenitor = nomeGenitorField.getText();
            String nomeGenitora = nomeGenitoraField.getText();
            LocalDate dataNascimento = LocalDate.parse(dataNascimentoField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String sexo = sexoField.getText();
            LocalDate dataRegistro = LocalDate.parse(dataRegistroField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            controller.registrar(termo, livro, folha, nome, nomeGenitor, nomeGenitora, dataNascimento, sexo, dataRegistro);
            JOptionPane.showMessageDialog(this, "Registro salvo com sucesso!");
            listarRegistros();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        } finally {
            registrarButton.setEnabled(true);
        }
    }

    private void listarRegistros() {
        listModel.clear();
        List<Registro> registros = registroDao.getAll();
        registros.forEach(registro -> listModel.addElement("Termo: " + registro.getTermo() + " - Nome: " + registro.getNome()));
    }

    private void excluirRegistro() {
        String selecionado = listaRegistros.getSelectedValue();
        if (selecionado != null) {
            int termo = Integer.parseInt(selecionado.split(" ")[1]); // Ajuste conforme necessário
            registroDao.delete(termo);
            JOptionPane.showMessageDialog(this, "Registro excluído com sucesso!");
            listarRegistros(); // Atualiza a lista após exclusão
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro para excluir.");
        }
    }

    public static void main(String[] args) {
        new RegistroGUI();
    }
}

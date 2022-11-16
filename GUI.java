import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class GUI {
    /**
     * createAndDisplay creates JFrame with container containing JPanels
     */
    public static void createAndDisplay() {
        JFrame frame = new JFrame("Min kalender");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel container = new JPanel();
        container.setSize(900, 500);
        container.setBackground(Color.WHITE);

        JPanel panelColorChange = new JPanel();
        panelColorChange.setLayout(new BoxLayout(panelColorChange, BoxLayout.Y_AXIS));

        JButton buttonChangeColor = new JButton("FÄRG!!");
        addButtonListener(buttonChangeColor, container);

        panelColorChange.add(buttonChangeColor);
        container.add(panelColorChange);

        // Loop creates one JPanel for each day of the week
        int x = 1;
        for (int i = 1; i <= 7; i++) {
            addGroupOfComponents(getDate(x), container, x);
            x++;
        }

        frame.add(container);
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * Adds components to JPanel (one panel per day of the week)
     */
    private static void addGroupOfComponents(String text, JPanel container, int x) {
        Font font1 = new Font(Font.DIALOG, Font.BOLD, 15);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        // JLabel showing date
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(90, 100));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(font1);

        // JTextArea where input is shown after button click
        JTextArea cal = new JTextArea();
        cal.setPreferredSize(new Dimension(80, 300));
        cal.setEditable(false);
        cal.setBorder(BorderFactory.createLineBorder(Color.black));

        // JTextField for typing
        JTextField input = new JTextField("Skriv något");
        input.setBorder(BorderFactory.createLineBorder(Color.black));

        // JButton sending input text to TextArea
        JButton button = new JButton("Lägg till");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButtonListener(button, input, cal);

        panel.add(label);
        panel.add(cal);
        panel.add(input);
        panel.add(button);

        checkDate(x, panel);

        container.add(panel);

    }

    /**
     * ButtonListener to add input text to calendar JTextArea.
     */
    private static void addButtonListener(JButton button, JTextField tf, JTextArea ta) {
        ActionListener buttonListener = e -> {
            ta.append(tf.getText() + "\n");
            tf.setText("");
        };
        button.addActionListener(buttonListener);
    }

    /**
     * ButtonListener for "Färg!" button to change container background color.
     */
    private static void addButtonListener(JButton buttonChangeColor, JPanel container) {
        ActionListener buttonListener = e -> {
            ColorChange.colorChange(container);
        };
        buttonChangeColor.addActionListener(buttonListener);
    }

    /**
     * Gets date of day (1-7)
     */
    private static String getDate(int x) {
        LocalDate today = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.GERMANY).dayOfWeek();
        LocalDate date = today.with(fieldISO, x);
        return date.toString();
    }

    /**
     * Checks if today's date is same as any day in calendar, and changes color of that day
     */
    private static void checkDate(int x, JPanel panel) {
        LocalDate today = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.GERMANY).dayOfWeek();
        LocalDate date = today.with(fieldISO, x);

        if (date.equals(today)) {
            panel.setBackground(Color.pink);

        } else {
            panel.setBackground(Color.WHITE);
        }
    }
}

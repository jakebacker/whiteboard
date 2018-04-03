import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Whiteboard extends JFrame {

	JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn, magentaBtn;
	Board board;
	ActionListener actionListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == clearBtn) {
				board.clear();
			} else if (e.getSource() == blackBtn) {
				board.black();
			} else if (e.getSource() == blueBtn) {
				board.blue();
			} else if (e.getSource() == greenBtn) {
				board.green();
			} else if (e.getSource() == redBtn) {
				board.red();
			} else if (e.getSource() == magentaBtn) {
				board.magenta();
			}
		}
	};

	public Whiteboard() {
		board = new Board();
		addElements();

		setTitle("Whiteboard");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private void addElements() {
		Container content = this.getContentPane();
		// set layout on content pane
		content.setLayout(new BorderLayout());

		add(board, BorderLayout.CENTER);

		JPanel controls = new JPanel();

		clearBtn = new JButton("Clear");
		clearBtn.addActionListener(actionListener);
		blackBtn = new JButton("Black");
		blackBtn.addActionListener(actionListener);
		blueBtn = new JButton("Blue");
		blueBtn.addActionListener(actionListener);
		greenBtn = new JButton("Green");
		greenBtn.addActionListener(actionListener);
		redBtn = new JButton("Red");
		redBtn.addActionListener(actionListener);
		magentaBtn = new JButton("Magenta");
		magentaBtn.addActionListener(actionListener);

		// add to panel
		controls.add(greenBtn);
		controls.add(blueBtn);
		controls.add(blackBtn);
		controls.add(redBtn);
		controls.add(magentaBtn);
		controls.add(clearBtn);

		// add to content pane
		content.add(controls, BorderLayout.NORTH);
	}

	public Image getImage() {
		return board.getImage();
	}
}

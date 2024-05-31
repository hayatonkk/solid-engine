import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;

public class CountdownTimer extends JFrame {
    private JLabel countdownLabel; // カウントダウンの残り秒数を表示するラベル


    public CountdownTimer() {
        setTitle("2025年までの秒数カウントダウン"); // ウィンドウのタイトルを設定
        setSize(800, 500); // ウィンドウのサイズを設定
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ウィンドウを閉じたときにプログラムを終了する

        JPanel panel = new JPanel();
        countdownLabel = new JLabel("2025年までの残り秒数: "); // 初期テキストを設定
        countdownLabel.setFont(new Font("Helvetica", Font.PLAIN, 50)); // フォントサイズを設定

        panel.add(countdownLabel); // パネルにラベルを追加

        JButton startButton = new JButton("開始"); // 開始ボタンを作成
        startButton.setPreferredSize(new Dimension(300, 200)); // ボタンのサイズを設定
        startButton.setFont(new Font("Helvetica", Font.PLAIN, 38)); // ボタンのフォントサイズを設定
        startButton.addActionListener(new ActionListener() { // ボタンのクリックイベントを設定
            @Override
            public void actionPerformed(ActionEvent e) {
                startCountdown(); // カウントダウンを開始するメソッドを呼び出す
            }
        });
        panel.add(startButton); // パネルにボタンを追加

        add(panel);  // パネルをウィンドウに追加
        setVisible(true); // ウィンドウを表示する
    }

    private void startCountdown() {
        LocalDateTime targetDateTime = LocalDateTime.of(2025, 1, 1, 0, 0);
        long targetTime = targetDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // タイマーを1秒間隔で実行するように設定
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                long remainingSeconds = (targetTime - currentTime) / 1000; // 2025年までの残り秒数を計算
                SwingUtilities.invokeLater(() -> {
                    if (remainingSeconds <= 0) {
                        countdownLabel.setText("2025年になりました！"); // テキストを更新
                        timer.cancel();
                    } else {
                        countdownLabel.setText("2025年までの残り秒数: " + remainingSeconds); // 残り秒数を表示
                    }
                });
            }
        }, 0, 1000);
    }
}
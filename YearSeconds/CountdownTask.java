import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountdownTask implements ActionListener {
    private long targetTime; // カウントダウンの目標時間
    private JLabel countdownLabel; // 残り秒数を表示するラベル

    public CountdownTask (long targetTime, JLabel countdownLabel) {
        this.targetTime = targetTime; // 目標時間を設定
        this.countdownLabel = countdownLabel; // ラベルを設定
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long currentTime = System.currentTimeMillis();
        long secondsUntil2025 = (targetTime - currentTime) / 1000; // 2025年までの残り秒数を計算

        if (secondsUntil2025 < 0) { // 残り時間が過ぎた場合
            countdownLabel.setText("2025年までの残り秒数: 0 (過ぎました)"); // テキストを更新
            ((Timer) e.getSource()).stop(); // タイマーを停止
        } else {
            countdownLabel.setText("2025年までの残り秒数: " + secondsUntil2025); // 残り秒数を表示
        }
    }
}

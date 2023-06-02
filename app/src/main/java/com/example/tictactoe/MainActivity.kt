package com.example.tictactoe
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button as Button
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.tictactoe_krish.R

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var board: Array<Array<Button>>
    var PLAYER = true
    var TURN_COUNT = 0
    var boardStatus = Array(3){IntArray(3)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)
        val resetbtn: Button = findViewById(R.id.resetbtn)

        board = arrayOf(
            arrayOf(btn1, btn2, btn3),
            arrayOf(btn4, btn5, btn6),
            arrayOf(btn7, btn8, btn9)
        )

        for(i in board) {
            for(button in i){
                button.setOnClickListener(this)
            }
        }
        initializeBoardStatus()

        resetbtn.setOnClickListener{
            PLAYER = true
            TURN_COUNT = 0
            initializeBoardStatus()
        }

    }

    private fun initializeBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1
            }
        }
        for(i in board){
            for(button in i){
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btn1 ->{
                updateValue(row=0,col=0,player= PLAYER)
            }
            R.id.btn2 ->{
                updateValue(row=0,col=1,player= PLAYER)
            }
            R.id.btn3 ->{
                updateValue(row=0,col=2,player= PLAYER)
            }
            R.id.btn4 ->{
                updateValue(row=1,col=0,player= PLAYER)
            }
            R.id.btn5 ->{
                updateValue(row=1,col=1,player= PLAYER)
            }
            R.id.btn6 ->{
                updateValue(row=1,col=2,player= PLAYER)
            }
            R.id.btn7 ->{
                updateValue(row=2,col=0,player= PLAYER)
            }
            R.id.btn8 ->{
                updateValue(row=2,col=1,player= PLAYER)
            }
            R.id.btn9 -> {
                updateValue(row=2,col=2,player= PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER = !PLAYER

        if(PLAYER){
            updateDisplay("Player X Turn")
        }
        else{
            updateDisplay("Player 0 Turn")
        }
        if(TURN_COUNT == 9){
            updateDisplay("Game Draw")
            showToast("Game Draw!")
        }
        checkWinner()
    }

    private fun checkWinner() {
        //Horizontal Rows
        for (i in 0..2) {
            if ((boardStatus[i][0] == boardStatus[i][1]) && (boardStatus[i][0] == boardStatus[i][2])){
                if (boardStatus[i][0] == 1) {
                    updateDisplay("Player X Winner")
                    showToast("Player X Wins!")
                    break
                }
                else if (boardStatus[i][0] == 0) {
                    updateDisplay("Player O Winner")
                    showToast("Player O Wins!")
                    break
                }
            }
        }
        //Vertical Columns
        for (i in 0..2) {
            if ((boardStatus[0][i] == boardStatus[1][i]) && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    updateDisplay("Player X Winner")
                    showToast("Player X Wins!")
                    break
                } else if (boardStatus[0][i] == 0) {
                    updateDisplay("Player O Winner")
                    showToast("Player O Wins!")
                    break
                }
            }
        }
        //First Diagonal
        if (boardStatus[0][0] == boardStatus[1][1] && (boardStatus[0][0] == boardStatus[2][2])) {
            if (boardStatus[0][0] == 1) {
                updateDisplay("Player X Winner")
                showToast("Player X Wins!")
            } else if (boardStatus[0][0] == 0) {
                updateDisplay("Player 0 Winner")
                showToast("Player O Wins!")
            }
        }

        //Second Diagonal
        if (boardStatus[0][2] == boardStatus[1][1] && (boardStatus[0][2] == boardStatus[2][0])) {
            if (boardStatus[0][2] == 1) {
                updateDisplay("Player X Winner")
                showToast("Player X Wins!")
            } else if (boardStatus[0][2] == 0) {
                updateDisplay("Player 0 Winner")
                showToast("Player O Wins!")
            }
        }

    }


    private fun updateDisplay(text: String) {
        val displayTv:TextView=findViewById(R.id.displayTv)
        displayTv.text = text
        if(text.contains("Winner")){
            disableButton()
        }
    }

    private fun disableButton(){
        for(i in board) {
            for(button in i){
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {

        val text: String = if(player) "X" else "O"
        val value: Int = if(player) 1 else 0
        board[row][col].apply{
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
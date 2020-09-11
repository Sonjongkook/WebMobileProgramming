
//Using DOM model, get each element by id
let user = document.getElementById('user')
let computer = document.getElementById('computer')
let result = document.getElementById('result')
let buttons = document.querySelectorAll("button")

//Add event lister on click event for all buttons
for (let i = 0; i <buttons.length; i++){
    buttons[i].addEventListener("click", Gameplay)
}



//By clicking one of the buttons, this function will be implemented
function Gameplay(b){
    // By using Math module, pick out random number between 1~3
    let computer_hand = Math.floor(Math.random()*3 +1)
    // assign button's inner text as a user selection
    let user_hand = b.target.innerText


    // converting random number 1 ,2, 3 to rock,paper,scissors
    if (computer_hand==1){
        computer_hand="scissors"
    }else if(computer_hand==2){
        computer_hand="rock"
    }else{
        computer_hand="paper"
    }

    // case when user selection is rock
    if (user_hand=="rock"){
        if(computer_hand =="rock"){
            //Inputting appropriate image and word for each element
            result.innerHTML = "Even"
            user.innerHTML = '<img src="../images/rock.png">'
            computer.innerHTML = '<img src="../images/rock.png">'
            window.alert("Even")
        }
        else if(computer_hand =="paper"){
            result.innerHTML = "You lose"
            user.innerHTML = '<img src="../images/rock.png">'
            computer.innerHTML = '<img src="../images/paper.png">'
            window.alert("Computer Win")
        }
        else{
            result.innerHTML = "You win"
            user.innerHTML = '<img src="../images/rock.png">'
            computer.innerHTML = '<img src="../images/scissors.png">'
            window.alert("User win")
        }
    }

    // case when user selection is scissors
    if (user_hand=="scissors"){
        if(computer_hand =="scissors"){
            result.innerHTML = "Even"
            user.innerHTML = '<img src="../images/scissors.png">'
            computer.innerHTML = '<img src="../images/scissors.png">'
            window.alert("Even")
        }
        else if(computer_hand =="rock"){
            result.innerHTML = "You lose"
            user.innerHTML = '<img src="../images/scissors.png">'
            computer.innerHTML = '<img src="../images/rock.png">'
            window.alert("Computer Win")
        }
        else{
            result.innerHTML = "You win"
            user.innerHTML = '<img src="../images/scissors.png">'
            computer.innerHTML = '<img src="../images/paper.png">'
            window.alert("User win")
        }
    }

    // case when user selection is paper
    if (user_hand=="paper"){
        if(computer_hand =="paper"){
            result.innerHTML = "Even"
            user.innerHTML = '<img src="../images/paper.png">'
            computer.innerHTML = '<img src="../images/paper.png">'
            window.alert("Even")
        }
        else if(computer_hand =="scissors"){
            result.innerHTML = "You lose"
            user.innerHTML = '<img src="../images/paper.png">'
            computer.innerHTML = '<img src="../images/scissors.png">'
            window.alert("Computer Win")
        }
        else{
            result.innerHTML = "You win"
            user.innerHTML = '<img src="../images/paper.png">'
            computer.innerHTML = '<img src="../images/rock.png">'
            window.alert("User win")
        }
    }

}



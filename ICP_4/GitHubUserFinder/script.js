function getGithubInfo(user) {
    //1. Create an instance of XMLHttpRequest class and send a GET request using it.
    // The function should finally return the object(it now contains the response!)]

    //object to perform an HTTP or API
    let xhttp = new XMLHttpRequest()
    //URL to which request is being made
    let url = `https://api.github.com/users/${user}`;

    //Using get method, async is true notification of a completed transaction is provided using event listeners
    xhttp.open("GET",url,true)
    //sends the request to the server
    xhttp.send()

    //the function to be executed when the readyState changes.
    xhttp.onreadystatechange = function () {
        if(xhttp.readyState === 4){
            //if the response is successful show the user's details
            console.log("success")
            if(xhttp.status === 200){
                //convert the data from api to readable format
                let response = JSON.parse(xhttp.responseText)
                showUser(response)
            //else display suitable message
            }else {
                noSuchUser(username)
            }
        }
    }
}

function showUser(user) {
    //2. set the contents of the h2 and the two div elements in the div '#profile' with the user content

    //clear the past research record
    $('.avatar').html(``)
    $('.information').html(``)

    $('#profile').children('h2').text(`User Name: ${user.login}`)
    $('.avatar').append(`<img style="width: 300px; height:300px" src=${user.avatar_url}>`)
    //input personal information on information div using append
    $('.information').append(`<div>Location: ${user.location}</div>`)
    $('.information').append(`<div>Full name: ${user.name}</div>`)
    $('.information').append(`<div>Following: ${user.following}</div>`)
    $('.information').append(`<div>Followers: ${user.followers}</div>`)
    $('.information').append(`<div>Repositories: ${user.public_repos}</div>`)
    $('.information').append(`<div>Github ID: ${user.id}</div>`)
    $('.information').append(`<div>Company: ${user.company}</div>`)
    $('.information').append(`<div>Bio: ${user.bio}</div>`)
    $('.information').append(`<div><a href=${user.html_url}>Github Url</a></div>`)

}

function noSuchUser(username) {
    //3. set the elements such that a suitable message is displayed
    $('#profile').children('h2').text("There is no such User")
    $('.avatar').html(``)
    $('.information').html(``)


}

$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        //check if the enter(i.e return) key is pressed
        if (e.which == 13) {
            //get what the user enters
            username = $(this).val();
            //reset the text typed in the input
            $(this).val("");
            //get the user's information and store the respsonse
            getGithubInfo(username);
            //move the source code to upward
        }
    })
});

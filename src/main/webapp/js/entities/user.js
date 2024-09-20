let firstName;
let lastName;
let password;
let username;
let dateOfBirth;

const userInit = async (e) => {
    let btn = document.getElementById('addUser');
    btn.addEventListener('click', await addUser);
    let form = document.getElementById('userForm').addEventListener('submit', e => {
        e.preventDefault();
        e.stopPropagation();
    })
    let btn2 = document.getElementById('delete').addEventListener('click', await deleteUser)
}

const addUser = async (e) => {
    e.preventDefault();
    getUserData();
    let entityData = createUserObject();
    console.log(entityData);
    return await postOrPutFetch('POST',entityData, "users");


}

const deleteUser = async (e) => {
    e.preventDefault();
    return await getOrDeleteFetch('DELETE', 'users');
}

const createUserObject = () => {
    return {
        firstName: firstName,
        lastName: lastName,
        password: password,
        username: username,
        dateOfBirth: dateOfBirth
    };
}

const getUserData = () => {
    firstName = document.getElementById('firstName').value;
    lastName = document.getElementById('lastName').value;
    password = document.getElementById('password').value;
    username = document.getElementById('username').value;
    dateOfBirth = document.getElementById('dateOfBirth').value;
}

window.onload = userInit;
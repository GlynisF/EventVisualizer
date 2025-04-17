<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 3/17/25
  Time: 12:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html data-md-theme="light">
<head>
    <title>Material</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="https://fonts.googleapis.com/css2?family=Material+Icons"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined"
          rel="stylesheet">
    <link rel="stylesheet" href="app/theme.min.css"/>
    <script type="module" src="./app/index.js"></script>



</head>
<body>
<md-tab-bar>
<md-tabs>
    <md-primary-tab active inline-icon>
        <md-icon slot="icon">piano</md-icon>
        Keyboard
    </md-primary-tab>
    <md-primary-tab inline-icon>
        <md-icon slot="icon">tune</md-icon>
        Guitar
    </md-primary-tab>
</md-tabs>
</md-tab-bar>
<md-pane>
    <div>Your sidebar or panel content</div>
</md-pane>

<md-window>
    <div>Your main window content</div>
</md-window>

<md-layout-grid>
<h1 class="md-typescale-display-medium">Hello Material!</h1>
<form>
    <p class="md-typescale-body-medium">Check out these controls in a form!</p>
    <md-checkbox></md-checkbox>
    <div>
        <md-radio name="group"></md-radio>
        <md-radio name="group"></md-radio>
        <md-radio name="group"></md-radio>
    </div>
    <md-outlined-text-field label="Favorite color" value="Purple"></md-outlined-text-field>
    <md-outlined-button class="reset-btn" type="reset">Reset</md-outlined-button>
</form>
</md-layout-grid>

<form>
    <label class="mdc-text-field mdc-text-field--filled">
        <span class="mdc-text-field__ripple"></span>
        <span class="mdc-floating-label mdc-text-field--label-floating" id="my-label-id">Hint text</span>
        <input class="mdc-text-field__input" type="text" aria-labelledby="my-label-id">
        <span class="mdc-line-ripple"></span>
    </label>
</form>
<div class="container-body md-container">

</div>

<div class="mdc-touch-target-wrapper">
    <button style="position: relative">
        <md-focus-ring style="--md-focus-ring-shape: 8px"></md-focus-ring>
    </button>

</div>
<script src="./bundle.js"></script><script>

    const topAppBarElement = document.querySelector('.mdc-top-app-bar--dense');
    //const topAppBar = new MDCTopAppBar(topAppBarElement);
    MDCRipple.attachTo(document.querySelector('.mdc-button--raised'));

</script>

</body>
</html>

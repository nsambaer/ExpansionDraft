<template>
    <div class="header">
        <header>
            <router-link :to="{name: 'home'}">
            <img src="../assets/logo.jpg" />
            </router-link>
        </header>
        <nav>
            <ul>
            <router-link :to="{name: 'players'}"><li class="listitem">Players</li></router-link>
            <router-link :to="{name: 'teams'}"><li class="listitem">Teams</li></router-link>
            <router-link :to="{name: 'louisville'}"><li class="listitem">Louisville</li></router-link>
            </ul>
        </nav>
    </div>

</template>

<script>
export default {
    name: 'app-header'

}
</script>

<style>

.header {
    background-color:#aaaaaa;
    display: grid;
    grid-template-columns: 1fr 3fr;
    grid-template-areas: 
    "header nav nav nav";
    max-height: 100px;
}

header {
    grid-area: header;
    display: flex;

}

img {
    max-height: 75px;
    padding: 5px 30px;
}

h2 {
    padding: 10px 8px;
    margin: 0em;
}

nav {
    margin: 0px 2px;
    padding: 0;
    grid-area: nav;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

ul {
    display: flex;
    justify-content: flex-end;
    flex-direction: row;
    padding: 0px 30px;
    
}

a {
    text-decoration: none;
}

.listitem {
    list-style: none;
    background-color: #C1272D;
    border-radius: 5px;
    text-align: center;
    line-height: auto;
    margin: 0px 7px 0px 7px;
    padding: 10px 35px 10px 35px;
    font-size: x-large;
    color: #000000;
    
}

@media (max-width: 768px) {
    .header {
        display: grid;
        grid-template-columns: 1fr;
        grid-template-areas: 
        "header"
        "nav";
    }
}




</style>
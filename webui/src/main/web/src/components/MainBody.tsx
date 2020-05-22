import React from "react";
import {Route, Switch} from "react-router-dom";
import ListItems from "./ListItems";
import AddItem from "./AddItem";

const MainBody = () => (
    <main className="col-md-9 ml-sm-auto col-lg-10 px-4" role="main">
        <Switch>
            <Route path="/:feature/manage"><ListItems/></Route>
            <Route path="/:feature/new"><AddItem/></Route>
        </Switch>
    </main>
);

export default MainBody;

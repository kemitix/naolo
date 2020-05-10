import React from "react";
import {Route, Switch} from "react-router-dom";
import ManageItems from "./ManageItems";

const MainBody = () => (
    <main className="col-md-9 ml-sm-auto col-lg-10 px-4" role="main">
        <Switch>
            <Route path="/:feature/manage">
                <ManageItems/>
            </Route>
        </Switch>
    </main>
);

export default MainBody;

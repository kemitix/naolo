import React from "react";
import {Route, Switch} from "react-router-dom";
import ManageItems from "./ManageItems";

const MainBody = () => (
    <div className="fl w-80 bg-light-gray tc">
        <Switch>
            <Route path="/:feature/manage">
                <ManageItems/>
            </Route>
        </Switch>
    </div>
);

export default MainBody;

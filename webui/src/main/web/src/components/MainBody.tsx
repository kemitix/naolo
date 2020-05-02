import React from "react";
import {Route, Switch} from "react-router-dom";
import FeatureItem from "./FeatureItem";

const MainBody = () => (
    <div className="fl w-80 bg-light-gray tc">
        <Switch>
            <Route path="/:feature/:item">
                <FeatureItem/>
            </Route>
        </Switch>
    </div>
);

export default MainBody;

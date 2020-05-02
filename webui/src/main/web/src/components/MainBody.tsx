import React from "react";
import {Route, Switch, useParams} from "react-router-dom";

export const FeatureItem = () => {
    const {feature, item} = useParams();
    return (
        <>
            <p>feature: {feature}</p>
            <p>item: {item}</p>
        </>
    );
};

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

import React from "react";
import {
    BrowserRouter as Router,
    Route,
    Switch,
    useParams
} from "react-router-dom";

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
    <Router>
        <div className="fl w-80 bg-light-gray tc">
            <Switch>
                <Route path="/:feature/:item">
                    <FeatureItem/>
                </Route>
            </Switch>
        </div>
    </Router>
);

export default MainBody;

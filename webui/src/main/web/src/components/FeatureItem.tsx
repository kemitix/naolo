import React from "react";
import {useParams} from "react-router-dom";

const FeatureItem = () => {
    const {feature, item} = useParams();
    return (
        <>
            <p>feature: {feature}</p>
            <p>item: {item}</p>
        </>
    );
};

export default FeatureItem;

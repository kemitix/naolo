import React from "react";
import {useParams} from "react-router-dom";

const ManageItems = () => {
    const {feature} = useParams();
    return (
        <>
            <p>feature: {feature}</p>
            <p>item: manage</p>
        </>
    );
};

export default ManageItems;

import React, {ChangeEvent, useState} from "react";
import {useParams} from "react-router-dom";

const AddItem = () => {
    const {feature, action} = useParams();

    const [textValue, setTextValue] = useState("");

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setTextValue(event.currentTarget.value);
    }

    return (
        <form>
            <h2>{feature}</h2>
            <h3>{action} {feature}</h3>
            <input type="text" onChange={handleChange} value={textValue}/>
            <input type="submit" value="Save" />
        </form>
    );
}

export default AddItem;

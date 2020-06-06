import React, {ChangeEvent} from "react";
import {Field} from "./ItemList";

interface EnumerationFieldProps {
    value: string;
    meta: Field;
    onChange: (event: ChangeEvent<HTMLSelectElement>) => void;
    options: Array<string>
}

const EnumerationField = (props: EnumerationFieldProps) => {
    return (
        <div className="form-group">
            <p>Value: {props.value}</p>
            <label htmlFor={props.meta.name}>{props.meta.label}</label>
            <select className="form-control"
                    name={props.meta.name}
                    id={props.meta.name}
                    value={props.value}
                    onChange={props.onChange}>
                <option>--- Select ---</option>
                {/* needs to inject current value */}
                {props.options.map(o =>
                    (<option key={o.valueOf()}>
                        {o}
                    </option>))}
            </select>
        </div>
    );
}

export default EnumerationField;

import React from "react";

interface NavItemProps {
    label: string;
    enabled: boolean;
    uri: string
}

const NavItem = (props: NavItemProps) => {

    const navItemStyle = "f4 fw7 dib pa2 no-underline";
    const enabledStyle = "bg-animate bg-white hover-bg-light-blue black";
    const disabledStyle = "bg-near-white gray";

    const enabledItem = (
        <a href={props.uri} className={enabledStyle + " " + navItemStyle}>
            {props.label}
        </a>);
    const disabledItem = (
        <span className={disabledStyle + " " + navItemStyle}>
            {props.label}
        </span>
    );
    return (
        <li className="pa4 lh-copy">
            {props.enabled
                ? enabledItem
                : disabledItem}
        </li>
    );
};

export default NavItem;

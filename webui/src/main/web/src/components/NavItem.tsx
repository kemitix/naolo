import React from "react";
import {NavigationItem} from "./Navigation";

interface NavItemProps {
    item: NavigationItem,
    uri: string,
    baseUri: string
}

const NavItem = (props: NavItemProps) => {

    const navItemStyle = "f4 fw7 dib pa2 no-underline";
    const enabledStyle = "bg-animate bg-white hover-bg-light-blue black";
    const disabledStyle = "bg-near-white gray";

    const enabledItem = (
        <a href={props.uri} className={enabledStyle + " " + navItemStyle}>
            <img src={props.baseUri + props.item.enabledIcon} alt=""/>
            {props.item.name}
        </a>);
    const disabledItem = (
        <span className={disabledStyle + " " + navItemStyle}>
            <img src={props.baseUri + props.item.disabledIcon} alt=""/>
            {props.item.name}
        </span>
    );
    return (
        <div className="pa4 lh-copy">
            {props.item.enabled
                ? enabledItem
                : disabledItem}
        </div>
    );
};

export default NavItem;

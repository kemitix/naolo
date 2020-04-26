import React from "react";
import NavItem from "./NavItem";

const Navigation = () => (
    <nav className="fl w-20 bg-near-white tc">
        <div>Navigation side bar</div>
        <ul>
            <NavItem key={1} label="item 1" enabled={true} uri="/item1/"/>
            <NavItem key={2} label="item 2" enabled={false} uri="/item2/"/>
            <NavItem key={3} label="item 3" enabled={true} uri="/item3/"/>
        </ul>
    </nav>
);

export default Navigation;

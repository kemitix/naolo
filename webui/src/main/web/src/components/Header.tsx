import React from "react";
import {Link} from "react-router-dom";

interface HeaderProps {
    homePath: string
}

const Header = (props: HeaderProps) => (
    <div className="flex items-center justify-center pa4 bg-lightest-blue navy">
        <p className="lh-title ml3">
            <Link to={"/" + props.homePath + "/"}>Naolo - Pet Clinic</Link>
        </p>
    </div>);

export default Header;

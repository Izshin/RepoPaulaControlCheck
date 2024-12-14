import { useState } from "react";
import { Table } from "reactstrap";
import tokenService from "../services/token.service";
import useFetchState from "../util/useFetchState";

//Hecho con gpt, se ve como el orto pero pasa el test

const user = tokenService.getUser();
const jwt = tokenService.getLocalAccessToken();

export default function SurgeryTypeListing() {
    const [message, setMessage] = useState(null);
    const [visible, setVisible] = useState(false);
    
    // Obtener los tipos de cirugía desde la API
    const [surgeryTypes, setsurgeryTypes] = useFetchState(
        [],
        `/api/v1/surgerytypes`,
        jwt,
        setMessage,
        setVisible
    );    

    // Generar filas de tabla para los tipos de cirugía
    const surgeryTypesList = surgeryTypes.map((surgeryType) => (
        <tr key={surgeryType.id}>
            <td className="text-center">{surgeryType.name}</td>
        </tr>
    ));

    // Extraer nombres únicos de tipos de mascotas de los tipos de cirugía
    const petTypeNamesSet = new Set();
    surgeryTypes.forEach((surgeryType) => {
        if (surgeryType.susceptiblePetTypes) {
            surgeryType.susceptiblePetTypes.forEach((petType) => {
                if (petType && petType.name) {
                    petTypeNamesSet.add(petType.name);
                }
            });
        }
    });

    // Convertir el set a una lista y mapear a elementos de lista
    const petTypesList = Array.from(petTypeNamesSet).map((name) => (
        <li key={name}>{name}</li>
    ));

    return (
        <>
            <div>
                {/* Lista de Tipos de Cirugía */}
                <Table aria-label="SurgeryTypes" className="mt-4">
                    <thead>
                        <tr>
                            <th width="15%" className="text-center">Name</th>
                        </tr>
                    </thead>
                    <tbody>{surgeryTypesList}</tbody>
                </Table>

                {/* Lista de Tipos de Mascotas */}
                <div className="mt-4">
                    <h3>Susceptible Pet Types</h3>
                    <ul>
                        {petTypesList}
                    </ul>
                </div>
            </div>
        </>
    );
}
